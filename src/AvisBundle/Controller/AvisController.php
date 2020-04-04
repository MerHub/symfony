<?php

namespace AvisBundle\Controller;

use AppBundle\Entity\chauffeur;
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
            'avi' => $avi,
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
        $avi = new Avis();
        $form = $this->createForm('AvisBundle\Form\AvisType', $avi);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->persist($avi);
            $em->flush();

            return $this->redirectToRoute('avis_index',[
                'idChauffeur'=>$avi->getIdChauffeur()
            ]);
        }

        return $this->render('@Avis/avis/new.html.twig', array(
            'avi' => $avi,
            'form' => $form->createView(),
            'idChauffeur'=>$idChauffeur,
            'idClient'=>$idClient,
        ));
    }

    /**
     * Finds and displays a avi entity.
     *
     */
    public function showAction(Avis $avi)
    {
        $deleteForm = $this->createDeleteForm($avi);

        return $this->render('@Avis/avis/show.html.twig', array(
            'avi' => $avi,
            'delete_form' => $deleteForm->createView(),
        ));
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

        return $this->render('@Avis/avis/edit.html.twig', array(
            'avi' => $avi,
            'edit_form' => $editForm->createView(),
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
