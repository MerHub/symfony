<?php

namespace ChauffeurBundle\Controller;

use AppBundle\Entity\chauffeur;
use AppBundle\Entity\Notification;
use AppBundle\Entity\user;
use AppBundle\Form\chauffeurType;
use AppBundle\Form\userType;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\File\Exception\FileException;
use Symfony\Component\HttpFoundation\File\UploadedFile;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;


class DefaultController extends Controller
{
    public function indexAction()
    {
        $user=$this->getUser();
        $id=$user->getId();
        $Puser=$this->getDoctrine()->getRepository(user::class)->find($id);
        $user=$this->getDoctrine()->getRepository(chauffeur::class)->find($id);

        if($user->getAdresse()==null || $user->getCin()==null || $user->getPermis()==null || $user->getNom()==null || $user->getPrenom()==null
            || $user->getPhoto()==null || $Puser->getNtel()==null){
            return $this->redirectToRoute("userProfile_page_chauffeur");
        }
        $notification=$this->getDoctrine()->getRepository(Notification::class)->findBy(["idReceive"=>$id,"seen"=>0]);
        return $this->render('@Chauffeur/Default/index.html.twig',[
            "notification"=>$notification,
            'user'=>$user
        ]);
    }

    public function ServiceSetChauffeurAction($adresse="",$cin="",$permis="",$nom="",$prenom="",$photo="",$id){

        $data=["requette"=>["reponse"=>"oui"]];
        $cins=$this->getDoctrine()->getRepository(chauffeur::class)->findBy(["cin"=>$cin]);
        $permiss=$this->getDoctrine()->getRepository(chauffeur::class)->findBy(["permis"=>$permis]);
        $chauffeur=new chauffeur();
        $chauffeur=$this->getDoctrine()->getRepository(chauffeur::class)->find($id);
        $chauffeur->setAdresse($adresse);
        if(sizeof($cins)!=0 && $cins[0]->getCin()!=$chauffeur->getCin()){
            $data=["requette"=>["reponse"=>"no","message"=>"CIN existe deja"]];
        }else{
            $chauffeur->setCin($cin);
        }
        if(sizeof($permiss)!=0 && $permiss[0]->getPermis()!=$chauffeur->getPermis()){
            $data=["requette"=>["reponse"=>"no","message"=>"permis existe deja"]];
        }else{
            $chauffeur->setPermis($permis);
        }
        $chauffeur->setNom($nom);
        $chauffeur->setPrenom($prenom);
        if($photo!="rien"){
            $photo=str_replace("&","/",$photo);
            $image_link = $photo;//Direct link to image
            $structure = __DIR__.'/../../../web/font/imgProfileUser/';
            $ex=$pieces = explode(".", $image_link);
            $destination=$structure.$id.".".end($ex);

            if(copy($photo, $destination)){
                $chauffeur->setPhoto($id.".".end($ex));
            }
        }

if($data["requette"]["reponse"]=="oui"){
    $em=$this->getDoctrine()->getManager();
    $em->persist($chauffeur);
    $em->flush();
}

        header('Content-type: application/json');
        return  new Response(json_encode( $data ));


    }

    public function userProfileAction(Request $request)
    {
        $user=$this->getUser();
        $id=$user->getId();
        $chauffeur=$this->getDoctrine()->getRepository(chauffeur::class)->find($id);
        $user=$this->getDoctrine()->getRepository(user::class)->find($id);
        $form=$this->createForm(chauffeurType::class,$chauffeur);
        $form=$form->handleRequest($request);
        if($form->isSubmitted() and $form->isValid()){

            $brochureFile = $form->get('photo')->getData();

            if ($brochureFile) {
                $originalFilename = pathinfo($brochureFile->getClientOriginalName(), PATHINFO_FILENAME);
                // this is needed to safely include the file name as part of the URL
                $safeFilename = transliterator_transliterate('Any-Latin; Latin-ASCII; [^A-Za-z0-9_] remove; Lower()', $originalFilename);
                $newFilename = $safeFilename.'-'.uniqid().'.'.$brochureFile->guessExtension();

                // Move the file to the directory where brochures are stored
                try {
                    $brochureFile->move(
                        $this->getParameter('imgProfileUser_directories'),
                        $newFilename
                    );
                } catch (FileException $e) {
                    // ... handle exception if something happens during file upload
                }

                // updates the 'brochureFilename' property to store the PDF file name
                // instead of its contents
                $chauffeur->setPhoto($newFilename);
            }


            $em=$this->getDoctrine()->getManager();
            $em->flush();
            return $this->render('@Chauffeur/Default/userProfile.html.twig',[
                "form"=>$form->createView(),
                "message"=>"ok"
            ]);
        }

        return $this->render('@Chauffeur/Default/userProfile.html.twig',[
            "form"=>$form->createView()
        ]);
    }

    public function ShowAction()
    {
        $liste=$this->getDoctrine()->getRepository(chauffeur::class)->findAll();
        return $this->render('@Chauffeur/Default/ShowAvisReclamation.html.twig',[
            "listeChauffeur"=>$liste
        ]);
    }

}
