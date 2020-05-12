<?php

namespace TaxiBundle\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Response;
use TaxiBundle\Entity\Taxi;

class DefaultController extends Controller
{
    public function indexAction()
    {
        return $this->render('TaxiBundle:Default:index.html.twig');
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
}
