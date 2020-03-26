<?php

namespace TaxiBundle\Controller;

use TaxiBundle\Entity\CategorieTaxi;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;

/**
 * Categorietaxi controller.
 *
 */
class CategorieTaxiController extends Controller
{
    /**
     * Lists all categorieTaxi entities.
     *
     */
    public function indexAction()
    {
        $em = $this->getDoctrine()->getManager();

        $categorieTaxis = $em->getRepository('TaxiBundle:CategorieTaxi')->findAll();

        return $this->render('categorietaxi/index.html.twig', array(
            'categorieTaxis' => $categorieTaxis,
        ));
    }

    /**
     * Creates a new categorieTaxi entity.
     *
     */
    public function newAction(Request $request)
    {
        $categorieTaxi = new Categorietaxi();
        $form = $this->createForm('TaxiBundle\Form\CategorieTaxiType', $categorieTaxi);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $file=$categorieTaxi->getImage();
            $fileName=md5(uniqid()).'.'.$file->guessExtension();
            $file->move($this->getParameter('upload_directory'),$fileName);
            $categorieTaxi->setImage($fileName);
            $em->persist($categorieTaxi);
            $em->flush();

            return $this->redirectToRoute('categorietaxi_show', array('id' => $categorieTaxi->getId()));
        }

        return $this->render('categorietaxi/new.html.twig', array(
            'categorieTaxi' => $categorieTaxi,
            'form' => $form->createView(),
        ));
    }

    /**
     * Finds and displays a categorieTaxi entity.
     *
     */
    public function showAction(CategorieTaxi $categorieTaxi)
    {
        $deleteForm = $this->createDeleteForm($categorieTaxi);

        return $this->render('categorietaxi/show.html.twig', array(
            'categorieTaxi' => $categorieTaxi,
            'delete_form' => $deleteForm->createView(),
        ));
    }

    /**
     * Displays a form to edit an existing categorieTaxi entity.
     *
     */
    public function editAction(Request $request, CategorieTaxi $categorieTaxi)
    {
        $deleteForm = $this->createDeleteForm($categorieTaxi);
        $editForm = $this->createForm('TaxiBundle\Form\CategorieTaxiType', $categorieTaxi);
        $editForm->handleRequest($request);

        if ($editForm->isSubmitted() && $editForm->isValid()) {
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('categorietaxi_edit', array('id' => $categorieTaxi->getId()));
        }

        return $this->render('categorietaxi/edit.html.twig', array(
            'categorieTaxi' => $categorieTaxi,
            'edit_form' => $editForm->createView(),
            'delete_form' => $deleteForm->createView(),
        ));
    }

    /**
     * Deletes a categorieTaxi entity.
     *
     */
    public function deleteAction(Request $request, CategorieTaxi $categorieTaxi)
    {
        $form = $this->createDeleteForm($categorieTaxi);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->remove($categorieTaxi);
            $em->flush();
        }

        return $this->redirectToRoute('categorietaxi_index');
    }

    /**
     * Creates a form to delete a categorieTaxi entity.
     *
     * @param CategorieTaxi $categorieTaxi The categorieTaxi entity
     *
     * @return \Symfony\Component\Form\Form The form
     */
    private function createDeleteForm(CategorieTaxi $categorieTaxi)
    {
        return $this->createFormBuilder()
            ->setAction($this->generateUrl('categorietaxi_delete', array('id' => $categorieTaxi->getId())))
            ->setMethod('DELETE')
            ->getForm()
        ;
    }
}
