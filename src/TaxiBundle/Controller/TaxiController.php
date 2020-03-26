<?php

namespace TaxiBundle\Controller;

use TaxiBundle\Entity\Taxi;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;

/**
 * Taxi controller.
 *
 */
class TaxiController extends Controller
{
    /**
     * Lists all taxi entities.
     *
     */
    public function indexAction()
    {
        $em = $this->getDoctrine()->getManager();

        $taxis = $em->getRepository('TaxiBundle:Taxi')->findAll();

        return $this->render('taxi/index.html.twig', array(
            'taxis' => $taxis,
        ));
    }

    /**
     * Creates a new taxi entity.
     *
     */
    public function newAction(Request $request)
    {
        $taxi = new Taxi();
        $form = $this->createForm('TaxiBundle\Form\TaxiType', $taxi);
        $form->handleRequest($request);
        $user=$this->getUser();

        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $file=$taxi->getPhoto();
            $fileName=md5(uniqid()).'.'.$file->guessExtension();
            $file->move($this->getParameter('upload_directory'),$fileName);
            $taxi->setPhoto($fileName);
            $taxi->setIdChauffeur($user);
            $em->persist($taxi);
            $em->flush();

            return $this->redirectToRoute('taxi_show', array('idTaxi' => $taxi->getIdtaxi()));
        }

        return $this->render('taxi/new.html.twig', array(
            'taxi' => $taxi,
            'form' => $form->createView(),
            'user'=>$user
        ));
    }

    /**
     * Finds and displays a taxi entity.
     *
     */
    public function showAction(Taxi $taxi)
    {
        $deleteForm = $this->createDeleteForm($taxi);

        return $this->render('taxi/show.html.twig', array(
            'taxi' => $taxi,
            'delete_form' => $deleteForm->createView(),
        ));
    }

    /**
     * Displays a form to edit an existing taxi entity.
     *
     */
    public function editAction(Request $request, Taxi $taxi)
    {
        $deleteForm = $this->createDeleteForm($taxi);
        $editForm = $this->createForm('TaxiBundle\Form\TaxiType', $taxi);
        $editForm->handleRequest($request);

        if ($editForm->isSubmitted() && $editForm->isValid()) {
            $em=$this->getDoctrine()->getManager();
            $file=$taxi->getPhoto();
            $fileName=md5(uniqid()).'.'.$file->guessExtension();
            $file->move($this->getParameter('upload_directory'),$fileName);
            $taxi->setPhoto($fileName);
            $em->flush();

            return $this->redirectToRoute('taxi_edit', array('idTaxi' => $taxi->getIdtaxi()));
        }

        return $this->render('taxi/edit.html.twig', array(
            'taxi' => $taxi,
            'edit_form' => $editForm->createView(),
            'delete_form' => $deleteForm->createView(),
        ));
    }

    /**
     * Deletes a taxi entity.
     *
     */
    public function deleteAction(Request $request, Taxi $taxi)
    {
        $form = $this->createDeleteForm($taxi);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->remove($taxi);
            $em->flush();
        }

        return $this->redirectToRoute('taxi_index');
    }

    /**
     * Creates a form to delete a taxi entity.
     *
     * @param Taxi $taxi The taxi entity
     *
     * @return \Symfony\Component\Form\Form The form
     */
    private function createDeleteForm(Taxi $taxi)
    {
        return $this->createFormBuilder()
            ->setAction($this->generateUrl('taxi_delete', array('idTaxi' => $taxi->getIdtaxi())))
            ->setMethod('DELETE')
            ->getForm()
        ;
    }
}
