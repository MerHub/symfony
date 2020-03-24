<?php

namespace ReclamationBundle\Controller;

use ReclamationBundle\Entity\typeReclamation;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;

/**
 * Typereclamation controller.
 *
 */
class typeReclamationController extends Controller
{
    /**
     * Lists all typeReclamation entities.
     *
     */
    public function indexAction()
    {
        $em = $this->getDoctrine()->getManager();

        $typeReclamations = $em->getRepository('ReclamationBundle:typeReclamation')->findAll();

        return $this->render('@Reclamation/typereclamation/index.html.twig', array(
            'typeReclamations' => $typeReclamations,
        ));
    }

    /**
     * Creates a new typeReclamation entity.
     *
     */
    public function newAction(Request $request)
    {
        $typeReclamation = new Typereclamation();
        $form = $this->createForm('ReclamationBundle\Form\typeReclamationType', $typeReclamation);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->persist($typeReclamation);
            $em->flush();

            return $this->redirectToRoute('typereclamation_show', array('id' => $typeReclamation->getId()));
        }

        return $this->render('@Reclamation/typereclamation/new.html.twig', array(
            'typeReclamation' => $typeReclamation,
            'form' => $form->createView(),
        ));
    }

    /**
     * Finds and displays a typeReclamation entity.
     *
     */
    public function showAction(typeReclamation $typeReclamation)
    {
        $deleteForm = $this->createDeleteForm($typeReclamation);

        return $this->render('@Reclamation/typereclamation/show.html.twig', array(
            'typeReclamation' => $typeReclamation,
            'delete_form' => $deleteForm->createView(),
        ));
    }

    /**
     * Displays a form to edit an existing typeReclamation entity.
     *
     */
    public function editAction(Request $request, typeReclamation $typeReclamation)
    {
        $deleteForm = $this->createDeleteForm($typeReclamation);
        $editForm = $this->createForm('ReclamationBundle\Form\typeReclamationType', $typeReclamation);
        $editForm->handleRequest($request);

        if ($editForm->isSubmitted() && $editForm->isValid()) {
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('typereclamation_edit', array('id' => $typeReclamation->getId()));
        }

        return $this->render('@Reclamation/typereclamation/edit.html.twig', array(
            'typeReclamation' => $typeReclamation,
            'edit_form' => $editForm->createView(),
            'delete_form' => $deleteForm->createView(),
        ));
    }

    /**
     * Deletes a typeReclamation entity.
     *
     */
    public function deleteAction(Request $request, typeReclamation $typeReclamation)
    {
        $form = $this->createDeleteForm($typeReclamation);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->remove($typeReclamation);
            $em->flush();
        }

        return $this->redirectToRoute('typereclamation_index');
    }

    /**
     * Creates a form to delete a typeReclamation entity.
     *
     * @param typeReclamation $typeReclamation The typeReclamation entity
     *
     * @return \Symfony\Component\Form\Form The form
     */
    private function createDeleteForm(typeReclamation $typeReclamation)
    {
        return $this->createFormBuilder()
            ->setAction($this->generateUrl('typereclamation_delete', array('id' => $typeReclamation->getId())))
            ->setMethod('DELETE')
            ->getForm()
        ;
    }
}
