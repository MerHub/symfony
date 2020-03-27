<?php

namespace AvisBundle\Controller;

use AvisBundle\Entity\Avis;
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
    public function indexAction()
    {
        $em = $this->getDoctrine()->getManager();

        $avis = $em->getRepository('AvisBundle:Avis')->findAll();

        return $this->render('@Avis/avis/index.html.twig', array(
            'avis' => $avis,
        ));
    }

    /**
     * Creates a new avi entity.
     *
     */
    public function newAction(Request $request)
    {
        $avi = new Avis();
        $form = $this->createForm('AvisBundle\Form\AvisType', $avi);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->persist($avi);
            $em->flush();

            return $this->redirectToRoute('avis_show', array('idAvis' => $avi->getIdavis()));
        }

        return $this->render('@Avis/avis/new.html.twig', array(
            'avi' => $avi,
            'form' => $form->createView(),
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
        $deleteForm = $this->createDeleteForm($avi);
        $editForm = $this->createForm('AvisBundle\Form\AvisType', $avi);
        $editForm->handleRequest($request);

        if ($editForm->isSubmitted() && $editForm->isValid()) {
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('avis_edit', array('idAvis' => $avi->getIdavis()));
        }

        return $this->render('@Avis/savis/edit.html.twig', array(
            'avi' => $avi,
            'edit_form' => $editForm->createView(),
            'delete_form' => $deleteForm->createView(),
        ));
    }

    /**
     * Deletes a avi entity.
     *
     */
    public function deleteAction(Request $request, Avis $avi)
    {
        $form = $this->createDeleteForm($avi);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->remove($avi);
            $em->flush();
        }

        return $this->redirectToRoute('avis_index');
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
