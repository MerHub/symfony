<?php

namespace AppBundle\Controller;

use AppBundle\Entity\chauffeur;
use AppBundle\Entity\user;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\HttpClient\HttpClient;
// version master
class DefaultController extends Controller
{
    /**
     * @Route("/login", name="loginpage")
     */
    public function loginAction(Request $request)
    {
        // replace this example code with whatever you need
        return $this->redirectToRoute("fos_user_security_login");
    }

    /**
     * @Route("/{_locale}", name="homepage")
     */
    public function indexAction(Request $request,$_locale='en')
    {$user = $this->getUser();
        if($user){

            if($user->getType()=="chauffeur"){
                return $this->redirectToRoute("chauffeur_homepage");
            }
            if($user->getType()=="client"){
                return $this->redirectToRoute("client_homepage");
            }

        }

        // replace this example code with whatever you need
        return $this->render('templateFront/index.html.twig');
    }

    /**
     * @Route("/GPS/{lat}/{lon}", name="updateGPS")
     */
    public function updateGPS($lat,$lon)
    {
        $user = $this->getUser();
        if($user!=null){
            $id=$user->getId();
            $user=$this->getDoctrine()->getRepository(user::class)->find($id);
            $user->setLongitude($lon);
            $user->setLatitude($lat);
            $this->getDoctrine()->getManager()->flush();
        }

        // replace this example code with whatever you need
        return new Response("ok");
    }

    /**
     * @Route("/AdreeseGPS/{lat}/{lon}", name="adresseGPS")
     */

    public function getAdresse($lat,$lon)
    {
        $client = HttpClient::create();
        $response = $client->request('GET', "https://nominatim.openstreetmap.org/reverse?format=json&lat=".$lat."&lon=".$lon);

        $statusCode = $response->getStatusCode();
// $statusCode = 200
        $contentType = $response->getHeaders()['content-type'][0];
// $contentType = 'application/json'
        $content = $response->getContent();
// $content = '{"id":521583, "name":"symfony-docs", ...}'
        $content = $response->toArray();

        return new Response($content["display_name"]);
    }


}
