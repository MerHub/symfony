<?php

namespace VeloBundle\Controller;

use VeloBundle\Entity\Location;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;

/**
 * Location controller.
 *
 */
class LocationController extends Controller
{
    /**
     * Lists all location entities.
     *
     */
    public function indexAction()
    {
        $em = $this->getDoctrine()->getManager();

        $locations = $em->getRepository('VeloBundle:Location')->findAll();

        return $this->render('@Velo/location/index.html.twig', array(
            'locations' => $locations,
        ));
    }

    /**
     * Creates a new location entity.
     *
     */
    public function newAction(Request $request)
    {
        $location = new Location();
        $location->setDateD(new \DateTime('now'));
        $location->setDateF(new \DateTime('now'));
        $form = $this->createForm('VeloBundle\Form\LocationType', $location);
        $form->handleRequest($request);
        $user=$this->getUser();
        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();

            $location->setIdClient($user);
            $em->persist($location);
            $em->flush();

            return $this->redirectToRoute('location_show', array('idLocation' => $location->getIdlocation()));
        }

        return $this->render('@Velo/location/new.html.twig', array(
            'location' => $location,
            'form' => $form->createView(),
            'user'=>$user
        ));
    }

    /**
     * Finds and displays a location entity.
     *
     */
    public function showAction(Location $location)
    {
        $deleteForm = $this->createDeleteForm($location);

        return $this->render('@Velo/location/show.html.twig', array(
            'location' => $location,
            'delete_form' => $deleteForm->createView(),
        ));
    }

    /**
     * Displays a form to edit an existing location entity.
     *
     */
    public function editAction(Request $request, Location $location)
    {
        $deleteForm = $this->createDeleteForm($location);
        $editForm = $this->createForm('VeloBundle\Form\LocationType', $location);
        $editForm->handleRequest($request);

        if ($editForm->isSubmitted() && $editForm->isValid()) {
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('location_edit', array('idLocation' => $location->getIdlocation()));
        }

        return $this->render('@Velo/location/edit.html.twig', array(
            'location' => $location,
            'edit_form' => $editForm->createView(),
            'delete_form' => $deleteForm->createView(),
        ));
    }

    /**
     * Deletes a location entity.
     *
     */
    public function deleteAction(Request $request, Location $location)
    {
        $form = $this->createDeleteForm($location);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->remove($location);
            $em->flush();
        }

        return $this->redirectToRoute('location_index');
    }

    /**
     * Creates a form to delete a location entity.
     *
     * @param Location $location The location entity
     *
     * @return \Symfony\Component\Form\Form The form
     */
    private function createDeleteForm(Location $location)
    {
        return $this->createFormBuilder()
            ->setAction($this->generateUrl('location_delete', array('idLocation' => $location->getIdlocation())))
            ->setMethod('DELETE')
            ->getForm()
        ;
    }
}
