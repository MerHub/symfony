<?php

namespace CLientBundle\Controller;

use AppBundle\Entity\chauffeur;
use AppBundle\Entity\Notification;
use AppBundle\Entity\user;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;

class DefaultController extends Controller
{
    public function indexAction()
    {

        $user=$this->getUser();
        $id=$user->getId();
        $Puser=$this->getDoctrine()->getRepository(user::class)->find($id);
        $listDriver=$this->getDoctrine()->getRepository(chauffeur::class)->findAll();
        if($Puser->getNtel()==null){
            return $this->redirectToRoute("userProfile_page_client");
        }
        $notification=$this->getDoctrine()->getRepository(Notification::class)->findBy(["idReceive"=>$id,"seen"=>0]);
        return $this->render('@CLient/Default/index.html.twig',[
            "notification"=>$notification
        ]);
    }

    public function userProfileAction()
    {
        return $this->render('@Chauffeur/Default/userProfile.html.twig');
    }


}
