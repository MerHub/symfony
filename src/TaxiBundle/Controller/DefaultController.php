<?php

namespace TaxiBundle\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\Controller;

class DefaultController extends Controller
{
    public function indexAction()
    {
        return $this->render('TaxiBundle:Default:index.html.twig');
    }
}
