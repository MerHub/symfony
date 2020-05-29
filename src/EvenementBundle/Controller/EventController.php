<?php

namespace EvenementBundle\Controller;

use AppBundle\Entity\user;
use EvenementBundle\Entity\Event;
use EvenementBundle\Entity\Inscription;
use http\Client;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Twilio\Http;



use Symfony\Component\HttpFoundation\JsonResponse;

/**
 * Event controller.
 *
 */
class EventController extends Controller
{
    /**
     * Lists all event entities.
     *
     */
    public function indexAction()
    {
        $em = $this->getDoctrine()->getManager();

        $events = $em->getRepository('EvenementBundle:Event')->findAll();


        return $this->render('@Evenement/event/index.html.twig', array(
            'events' => $events,
        ));
    }

        public function serviceShowListAction(){
            $list=$this->getDoctrine()->getRepository(Event::class)->findAll();
            $data=["liste"=>[]];
            forEach($list as $key=>$value){
                $dateAlle=$value->getDateAllee();
                $dateRetour=$value->getDateRetour();
                array_push($data["liste"],[
                    "idEvent"=>$value->getIdEvent(),
                    "nom"=>$value->getNom(),
                    "nbrPlace"=>$value->getNbrPlace(),
                    "depart"=>$value->getDepart(),
                    "arrivee"=>$value->getArrivee(),
                    "dateAllee"=>[
                        "annee"=>$dateAlle->format('Y'),
                        "mois"=>$dateAlle->format('m'),
                        "jour"=>$dateAlle->format('d'),
                        "heure"=>$dateAlle->format('H')+1,
                        "minute"=>$dateAlle->format('i'),
                        "seconde"=>$dateAlle->format('s')
                    ],
                    "dateRetour"=>[
                        "annee"=>$dateRetour->format('Y'),
                        "mois"=>$dateRetour->format('m'),
                        "jour"=>$dateRetour->format('d'),
                        "heure"=>$dateRetour->format('H')+1,
                        "minute"=>$dateRetour->format('i'),
                        "seconde"=>$dateRetour->format('s')
                    ],
                    "decription"=>$value->getDescription(),
                    "latitude1"=>$value->getLatitude1(),
                    "latitude2"=>$value->getLatitude2(),
                    "longitude1"=>$value->getLongitude1(),
                    "longitude2"=>$value->getLongitude2(),
                ]);
            }

            header('Content-type: application/json');
            return  new Response(json_encode( $data ));
        }
    public function frontAction()
    {
        $em = $this->getDoctrine()->getManager();

        $events = $em->getRepository('EvenementBundle:Event')->findAll();

        return $this->render('@Evenement/event/eventlistFront.html.twig', array(
            'events' => $events,
        ));
    }

    /**
     * Creates a new event entity.
     *
     */
    public function newAction(Request $request)
    {
        $event = new Event();
        $form = $this->createForm('EvenementBundle\Form\EventType', $event);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->persist($event);
            $em->flush();

            return $this->redirectToRoute('event_show', array('idEvent' => $event->getIdevent()));
        }

        return $this->render('@Evenement/event/new.html.twig', array(
            'event' => $event,
            'form' => $form->createView(),
        ));
    }

    /**
     * Finds and displays a event entity.
     *
     */
    public function showAction(Event $event)
    {
        $deleteForm = $this->createDeleteForm($event);



        return $this->render('@Evenement/event/show.html.twig', array(
            'event' => $event,
            'delete_form' => $deleteForm->createView(),
        ));
    }
    public function showwwwAction(Event $event)
    {
        $deleteForm = $this->createDeleteForm($event);



        return $this->render('@Evenement/event/show.html.twig', array(
            'event' => $event,
            'delete_form' => $deleteForm->createView(),
        ));
    }

    public function mail(\Swift_Mailer $mailer)
    {   $user=$this->getUser();
        $email=$user->getEmail();
        $message = (new \Swift_Message('Hello Email'))
            ->setFrom('Bolt2020@contact.com')
            ->setTo($email)
            ->setBody(
             "Vous etes inscrit"
            )
        ;
        $mailer->send($message);
    }
    public function sendMessageChauffeur($idReservation,$numClient,$idDriver)
    {
        //returns an instance of Vresh\TwilioBundle\Service\TwilioWrapper
        $twilio = $this->get('twilio.api');
        $numChauffeur=$this->getDoctrine()->getRepository(user::class)->find($idDriver)->getNTel();
        $message = $twilio->account->messages->sendMessage(
            '+12513130217', // From a Twilio number in your account
            '+216'.$numChauffeur, // Text any number
            "Connect you, you have an reservation : phone number : +216 ".$numClient
        );

        //get an instance of \Service_Twilio
        //$otherInstance = $twilio->createInstance('BBBB', 'CCCCC');

        return $this->redirectToRoute('_show', array('idReservation' => $idReservation));
    }

    public function senMessage()
    {
        //returns an instance of Vresh\TwilioBundle\Service\TwilioWrapper
        $twilio = $this->get('twilio.api');

        $message = $twilio->account->messages->sendMessage(
            '+12513130217', // From a Twilio number in your account
            '+21650081019', // Text any number
            "Connect you, you have an reservation : phone number : +216 "
        );

        //get an instance of \Service_Twilio
        //$otherInstance = $twilio->createInstance('BBBB', 'CCCCC');

        return $this->redirectToRoute('reservation_index');
    }


    public function addAction(Event $id2)
    {
        if ($id2->getNbrPlace()!=0)
        { $i = new Inscription();
        $nbr=$id2->getNbrPlace();
        $nbr--;
        $id2->setNbrPlace($nbr);
        $user=$this->getUser();
     //   $this->senMessage();
     //   $email=$user->getEmail();


           //    $transport = \Swift_MailTransport::newInstance();


          //  $transport = (new \Swift_SmtpTransport('smtp.mailtrap.io', 25))
      //          ->setUsername('0aeda355df9ad5')
        //        ->setPassword('7579b37003ae2d');



  /*              $msg = (new \Swift_Message('Hello Email'))
               ->setFrom('Bolt2020@contact.com')
               ->setTo($email)
               ->setBody(
                  'Vous etes inscrit'
              );
*/      //     $mailer= new  \Swift_Mailer($transport) ;

    //        $this->get('mailer')->send($msg);

         //   if (!$mailer->send($message, $failures))
           // { $mailer->send($message);
             //   echo "Failures:";
               // print_r($failures);
            //}
       //     $twilio = $this->get('twilio.api');

    /*       $sid = "ACa7cb1b635f46f7fedbf40bbf2c4e25af"; // Your Account SID from www.twilio.com/console
            $token = "520a62a1ef97242d042b0f044b564082"; // Your Auth Token from www.twilio.com/console

            $client = new CurlClient($sid, $token);
            $message = $client->messages->create(
                '+21650081019', // Text this number
                [
                    'from' => '+12513130217', // From a valid Twilio number
                    'body' => 'Hello You are Registred!'
                ]
            );
            */

        $id=$user->getId();
        $i->setIdEvent($id2);
        $Puser=$this->getDoctrine()->getRepository(\AppBundle\Entity\Client::class)->find($id);
        $i->setIdClient($Puser);
        $em = $this->getDoctrine()->getManager();
        $em->persist($i);
        $em->flush();


            $this->addFlash(
                'notice',
                'You are registred succssefully!'
            );

        return $this->redirectToRoute('reservation_index');

    }
    else{$this->addFlash('success', 'Article Created! Knowledge is power!');}
    }
    public function addEventAction(Request $request)
    {
        $em = $this->getDoctrine()->getManager();
        $livraison = new Livraison();
        $form = $this->createForm('bikeBundle\Form\LivraisonType', $livraison);

        $form->handleRequest($request);



        if ($form->isSubmitted() && $form->isValid()) {

            $em->persist($livraison);
            $em->flush();
            $liv=$em->getRepository('bikeBundle:Livreur')->find($livraison->getLivreur()->getId());
            $liv->setNbrlivraisonParjour(($liv->getNbrlivraisonParjour())+1);
            $em->flush();
            if(($liv->getNbrlivraisonParjour())>10){
                $liv->setLivDispo(0);
                $em->flush();
            }
            //Envoi d'un mail:
            // Create the Transport
            $transport = (new \Swift_SmtpTransport('smtp.gmail.com', 587, 'tls'))
                ->setUsername('razifertani1@gmail.com')
                ->setPassword('raziramla');

            /*
            You could alternatively use a different transport such as Sendmail:

            // Sendmail
            $transport = new Swift_SendmailTransport('/usr/sbin/sendmail -bs');
            */

            // Create the Mailer using your created Transport
            $mailer = new \Swift_Mailer($transport);

            // Create a message
            $message = (new \Swift_Message('Nouvelle Livraison'))
                ->setFrom('razifertani1@gmail.com')
                ->setTo($liv->getEmail())
                ->setBody(
                    '<html>' .
                    ' <body>' .
                    '<h4> Cher ' . $liv->getUsername() . ' vous avez une nouvelle livraison</h4> '.

                    '<h1> #REF: '.$livraison->getId().'</h1>'.
                    '<div style="color: #1e4c4a"> Vers: '.$livraison->getLivraisonAdresse().'</div>'.
                    '<div style="color: #1e4c4a"> Produits: '.$livraison->getLivraisonProduits().'</div>'.
                    '<div style="color: #1e4c4a"> Montant a payer: '.$livraison->getMontantTotal().'</div>'.
                    '</html>' .
                    ' </body>' ,'text/html'
                );

            // Send the message
            $mailer->send($message);
            ///////////////////


            return $this->redirectToRoute('table_content', array('id' => $livraison->getId()));
        }

        return $this->render('livraison/new.html.twig', array(
            'livraison' => $livraison,
            'form' => $form->createView(),
        ));
    }
    


    /**
     * Displays a form to edit an existing event entity.
     *
     */
    public function editAction(Request $request, Event $event)
    {
        $deleteForm = $this->createDeleteForm($event);
        $editForm = $this->createForm('EvenementBundle\Form\EventType', $event);
        $editForm->handleRequest($request);

        if ($editForm->isSubmitted() && $editForm->isValid()) {
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('event_index', array('idEvent' => $event->getIdevent()));
        }

        return $this->render('@Evenement/event/edit.html.twig', array(
            'event' => $event,
            'edit_form' => $editForm->createView(),
            'delete_form' => $deleteForm->createView(),
        ));
    }

    /**
     * Deletes a event entity.
     *
     */
    public function deleteAction(Request $request, Event $event)
    {
        $form = $this->createDeleteForm($event);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->remove($event);
            $em->flush();
        }

        return $this->redirectToRoute('event_index');
    }

    /**
     * Creates a form to delete a event entity.
     *
     * @param Event $event The event entity
     *
     * @return \Symfony\Component\Form\Form The form
     */
    private function createDeleteForm(Event $event)
    {
        return $this->createFormBuilder()
            ->setAction($this->generateUrl('event_delete', array('idEvent' => $event->getIdevent())))
            ->setMethod('DELETE')
            ->getForm()
        ;
    }
}
