<?php

namespace VeloBundle\Controller;

use AppBundle\Entity\Client;
use AppBundle\Entity\user;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use Symfony\Component\Serializer\Serializer;
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
    public function newAction(Request $request, $idVelo)
    {
        $location = new Location();
        $location->setDateD(new \DateTime('now'));
        $location->setDateF(new \DateTime('now'));


        $form = $this->createForm('VeloBundle\Form\LocationType', $location);
        $form->handleRequest($request);
        $user = $this->getUser();
        $velo = $this->getDoctrine()->getRepository(Velo::class)->findAll();
        $velo_s = $this->getDoctrine()->getRepository(Velo::class)->find($idVelo);

        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();

            $location->setIdVelo($velo_s);
            $location->setIdClient($user);

            $em->persist($location);
            $em->flush();

            return $this->redirectToRoute('location_show', array('idLocation' => $location->getIdlocation()));
        }

        return $this->render('@Velo/location/new.html.twig', array(
            'location' => $location,
            'form' => $form->createView(),
            'user' => $user,
            'velo' => $velo,
            'idSelect' => $idVelo
        ));
    }

    public function newMAction(Velo $idVelo, $Date_D, $Date_f, user $idclient, $prix)
    {
        $location = new Location();
        $location->setDateD(new \DateTime('now'));
        $location->setDateF(new \DateTime('now'));


        //   $user=$this->getUser();
        $velo = $this->getDoctrine()->getRepository(Velo::class)->findAll();
        $velo_s = $this->getDoctrine()->getRepository(Velo::class)->find($idVelo);


        $em = $this->getDoctrine()->getManager();
        try {
            $df = new \DateTime($Date_f);
            $dd=new \DateTime($Date_D);
        } catch (\Exception $e) {
        }
        $location->setIdVelo($velo_s);
        $location->setIdClient($idclient);
        $location->setDateF($df);
        $location->setDateD($dd);
        $location->setPrix($prix);
        $em->persist($location);
        $em->flush();
        header('Content-type: application/json');
        return  new Response(json_encode( ["reponse"=>"oui"] ));
    }

    /**
     * Finds and displays a location entity.
     *
     */
    public function prixAction(Request $request)
    {

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
    public function serviceShowListAction($idLocation){

        $list=$this->getDoctrine()->getRepository(Location::class)->findBy(["idLocation"=>$idLocation]);
        $data=["liste"=>[]];
        forEach($list as $key=>$value){
            $date_d=$value->getDateD();
            $date_f=$value->getDateF();
            array_push($data["liste"],[
                "idLocation"=>$value->getIdLocation(),
                "idClient"=>$value->getIdClient(),
                "idVelo"=>$value->getIdVelo(),


                "date_d"=>[
                    "annee"=>$date_d->format('Y'),
                    "mois"=>$date_d->format('m'),
                    "jour"=>$date_d->format('d'),
                    "heure"=>$date_d->format('H')+1,
                    "minute"=>$date_d->format('i'),
                    "seconde"=>$date_d->format('s')
                ],
                "date_f"=>[
                    "annee"=>$date_f->format('Y'),
                    "mois"=>$date_f->format('m'),
                    "jour"=>$date_f->format('d'),
                    "heure"=>$date_f->format('H')+1,
                    "minute"=>$date_f->format('i'),
                    "seconde"=>$date_f->format('s')
                ],
                "prix"=>$value->getPrix(),

            ]);
        }

        header('Content-type: application/json');
        return  new Response(json_encode( $data ));
    }
    /*
    public function showMAction(Location $location)
    {
        $deleteForm = $this->getDoctrine()->getRepository(Location::class)->find($location);
        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($deleteForm);
        return new JsonResponse($formatted);

    }
*/
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
            ->getForm();
    }
}
