<?php

namespace AvisBundle\Controller;

use AppBundle\Entity\chauffeur;
use AppBundle\Entity\Notification;
use AppBundle\Entity\user;
use AvisBundle\Entity\Avis;
use ChauffeurBundle\ChauffeurBundle;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;

/**
 * Avi controller.
 *
 */
class AvisController extends Controller
{
    /**
     * Lists all avi entities.
     *
     */
    public function indexAction(Request $request,$idChauffeur)
    {
        $idClient=$this->getUser()->getId();
        $em = $this->getDoctrine()->getManager();
        $avi = new Avis();
        $form = $this->createForm('AvisBundle\Form\AvisType', $avi);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->persist($avi);

            // notifi
            $titre = "Nouvel avis";
            $body = $avi->getMsg()." . Note = ".$avi->getNote();
            $operation="notif";
            $notification = new Notification();
            $notification
                ->setTitle($titre)
                ->setDescription($body)
                ->setIdSend($avi->getIdCclient()->getIdUser())
                ->setIdReceive($avi->getIdChauffeur()->getIdUser())
                ->setIcon($operation)
                ->setRoute('avis_show')
                ->setParameters(['idReceive'=>$avi->getIdChauffeur()->getIdUser()->getId()])
            ;
            $pusher = $this->get('mrad.pusher.notificaitons');
            $pusher->trigger($notification);
            $em->persist($notification);
                $em->flush();


            return $this->redirectToRoute('avis_index',[
                'idChauffeur'=>$avi->getIdChauffeur()->getIdUser()->getId()
            ]);
        }


        $avis = $em->getRepository('AvisBundle:Avis')->ListeAvis($idChauffeur);
        /**
         * @var $paginator \Knp\Component\Pager\Paginator
         */
        $paginator= $this->get("knp_paginator");
        $result= $paginator->paginate(
            $avis,
            $request->query->getInt('page', 1),
            $request->query->getInt('limit', 2));
        $chauffeur = $em->getRepository(chauffeur::class)->find($idChauffeur);

        return $this->render('@Avis/avis/index.html.twig', array(
            'avis' => $result,
            'chauffeur' => $chauffeur,
            'form' => $form->createView(),
            'idClient'=>$idClient,
        ));
    }

    /**
     * Creates a new avi entity.
     *
     */
    public function newAction(Request $request,$idChauffeur,$idClient)
    {
        // j'ai mélangé avec index
    }

    /**
     * Finds and displays a avi entity.
     *
     */
    public function showAction()
    {
        // j'ai mélangé avec index
    }

    /**
     * Displays a form to edit an existing avi entity.
     *
     */
    public function editAction(Request $request, Avis $avi)
    {
        $editForm = $this->createForm('AvisBundle\Form\AvisType', $avi);
        $editForm->handleRequest($request);

        if ($editForm->isSubmitted() && $editForm->isValid()) {
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('avis_index',[
                'idChauffeur'=>$avi->getIdChauffeur()->getIdUser()->getId()
            ]);
        }
        $chauffeur=$avi->getIdChauffeur();
        $idClient=$this->getUser()->getId();
        return $this->render('@Avis/avis/index.html.twig', array(
            'avis' => $avi,
            'chauffeur' => $chauffeur,
            'form' => $editForm->createView(),
            'idClient'=>$idClient,
        ));
    }

    /**
     * Deletes a avi entity.
     *
     */
    public function deleteAction(Avis $avi)
    {
        $em = $this->getDoctrine()->getManager();
        $em->remove($avi);
        $em->flush();

        return $this->redirectToRoute('avis_index',[
            'idChauffeur'=>$avi->getIdChauffeur()->getIdUser()->getId()
        ]);
    }

    /**
     * Creates a form to delete a avi entity.
     *
     * @param Avis $avi The avi entity
     *
     * @return \Symfony\Component\Form\Form The form
     */
    private function createDeleteForm(Avis $avi)
    {
        return $this->createFormBuilder()
            ->setAction($this->generateUrl('avis_delete', array('idAvis' => $avi->getIdavis())))
            ->setMethod('DELETE')
            ->getForm()
        ;
    }
}
