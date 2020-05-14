<?php

namespace TaxiBundle\Controller;

use AppBundle\Entity\chauffeur;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Response;
use TaxiBundle\Entity\CategorieTaxi;
use TaxiBundle\Entity\Taxi;

class DefaultController extends Controller
{
    public function indexAction()
    {
        return $this->render('TaxiBundle:Default:index.html.twig');
    }

    public function serviceGetCategorieTaxiAction(){
        $liste=$this->getDoctrine()->getRepository(CategorieTaxi::class)->findAll();
        $data=["liste"=>[]];

        foreach ($liste as $key=>$value){
            array_push($data["liste"],[
                "id"=>$value->getId(),
                "model"=>$value->getModel(),
                "image"=>$value->getImage(),
            ]);
        }
        header('Content-type: application/json');
        return  new Response(json_encode( $data ));
    }

    public function serviceGetTaxiAction($id_chauffeur){
        $taxi=new Taxi();
        $taxi=$this->getDoctrine()->getRepository(Taxi::class)->findOneBy(["idChauffeur"=>$id_chauffeur]);

        $data=["requette"=>"null"];
        if($taxi!=null){
            $data=["requette"=>["chauffeur"=>[
                "adresse"=>$taxi->getIdChauffeur()->getAdresse(),
                "cin"=>$taxi->getIdChauffeur()->getCin(),
                "id_user"=>$taxi->getIdChauffeur()->getIdUser()->getId(),
                "username"=>$taxi->getIdChauffeur()->getIdUser()->getUsername(),
                "email"=>$taxi->getIdChauffeur()->getIdUser()->getEmail(),
                "phone"=>$taxi->getIdChauffeur()->getIdUser()->getNTel(),
                "nom"=>$taxi->getIdChauffeur()->getNom(),
                "prenom"=>$taxi->getIdChauffeur()->getPrenom(),
                "photo_chauffeur"=>$taxi->getIdChauffeur()->getPhoto(),
            ],"categorieTaxi"=>[
                "photo_taxi"=>$taxi->getCategorie()->getImage()
            ],"taxi"=>[
                "id_taxi"=>$taxi->getIdTaxi(),
                "num_chassis"=>$taxi->getNumChassis(),
            ]]];
        }
        header('Content-type: application/json');
        return  new Response(json_encode( $data ));
    }

    public function serviceSetCategorieTaxiAction($numchassis,$idCat,$idChaufeur){
        $t=new Taxi();
        $t=$this->getDoctrine()->getRepository(Taxi::class)->findOneBy(["idChauffeur"=>$idChaufeur]);
        $cats=$this->getDoctrine()->getRepository(CategorieTaxi::class)->findOneBy(["id"=>$idCat]);
        $t->setNumChassis($numchassis);
        $t->setCategorie($cats);
        $chauffeur=$this->getDoctrine()->getRepository(chauffeur::class)->find($idChaufeur);
        $t->setIdChauffeur($chauffeur);
        $em=$this->getDoctrine()->getManager();
        $em->persist($t);
        $em->flush();
        header('Content-type: application/json');
        return  new Response(json_encode( ["requette"=>["reponse"=>"oui"]] ));
    }
}
