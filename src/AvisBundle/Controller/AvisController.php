<?php

namespace AvisBundle\Controller;

use AppBundle\Entity\chauffeur;
use AppBundle\Entity\Notification;
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

            $titre = "Nouvel avis";
            $body = $avi->getMsg()." &nbsp;&nbsp; Note = ".$avi->getNote();
            $operation="add";
            $notification = new Notification();
            $notification
                ->setTitle($titre)
                ->setDescription($body)
                ->setIdClient($avi->getIdCclient())
                ->setIdChauffeur($avi->getIdChauffeur())
                ->setIcon($operation)
                ->setRoute('comment_show')
                ->setParameters([])
            ;
            $em->persist($notification);
                $em->flush();


            return $this->redirectToRoute('avis_index',[
                'idChauffeur'=>$avi->getIdChauffeur()
            ]);
        }


        $avis = $em->getRepository('AvisBundle:Avis')->ListeAvis($idChauffeur);
        $chauffeur = $em->getRepository(chauffeur::class)->find($idChauffeur);

        return $this->render('@Avis/avis/index.html.twig', array(
            'avis' => $avis,
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
    public function showAction(Avis $avi)
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
                'idChauffeur'=>$avi->getIdChauffeur()
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
            'idChauffeur'=>$avi->getIdChauffeur()
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
