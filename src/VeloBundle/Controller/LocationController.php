<?php

namespace VeloBundle\Controller;

use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\HttpFoundation\Response;
use VeloBundle\Entity\Location;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;
use VeloBundle\Entity\Velo;
use Dompdf\Dompdf;
use Dompdf\Options;

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
    public function downloadAction()
    {

        // Configure Dompdf according to your needs
        $pdfOptions = new Options();
        $pdfOptions->set('defaultFont', 'Arial');

        // Instantiate Dompdf with our options
        $dompdf = new Dompdf($pdfOptions);
        $em = $this->getDoctrine()->getManager();

        $locations = $em->getRepository('VeloBundle:Location')->findAll();

        // Retrieve the HTML generated in our twig file
        $html = $this->renderView('@Velo/location/rent.html.twig', array(
            'locations' => $locations,));

        // Load HTML to Dompdf
        $dompdf->loadHtml($html);

        // (Optional) Setup the paper size and orientation 'portrait' or 'portrait'
        $dompdf->setPaper('A4', 'portrait');

        // Render the HTML as PDF
        $dompdf->render();

        // Output the generated PDF to Browser (inline view)
        $dompdf->stream("rent.pdf", [
            "Attachment" => true
        ]);
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
        $velo=$this->getDoctrine()->getRepository(Velo::class)->findAll();
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
            'user'=>$user,
            'velo'=>$velo
        ));
    }

    /**
     * Finds and displays a location entity.
     *
     */
    public function prixAction(Request $request){

        if (isset($_POST['d'])) {

            $prix = $this->getDoctrine()->getRepository('VeloBundle:Velo')->prix($_POST['d']);

            return new JsonResponse($prix[0]);
        }
        return new JsonResponse('ok');


    }
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

        return $this->redirectToRoute('location_new');
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
