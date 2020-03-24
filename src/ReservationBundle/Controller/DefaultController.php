<?php

namespace ReservationBundle\Controller;

use AppBundle\Entity\chauffeur;
use AppBundle\Entity\user;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;

class DefaultController extends Controller
{
    public function indexAction()
    {
        $user=$this->getUser();
        $id=$user->getId();
        $Puser=$this->getDoctrine()->getRepository(user::class)->find($id);
        $listDriver=$this->getDoctrine()->getRepository(user::class)->findBy(['type'=>'chauffeur']);
        return $this->render('@Reservation/Default/index.html.twig',[
            'user'=>$Puser,
            'listChauffeur'=>$listDriver
        ]);
    }
}
