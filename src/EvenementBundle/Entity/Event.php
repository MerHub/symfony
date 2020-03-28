<?php

namespace EvenementBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Event
 *
 * @ORM\Table(name="Event")
 * @ORM\Entity(repositoryClass="EvenementBundle\Repository\EventRepository")
 */
class Event
{
    /**
     * @var int
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="AUTO")
     * @ORM\Column(name="id_Event", type="integer")
     */
    private $idEvent;

    /**
     * @var string
     *
     * @ORM\Column(name="nom", type="string", length=255)
     */
    private $nom;

    /**
     * @var int
     *
     * @ORM\Column(name="nbr_place", type="integer")
     */
    private $nbrPlace;

    /**
     * @var string
     *
     * @ORM\Column(name="depart", type="string", length=255)
     */
    private $depart;

    /**
     * @var string
     *
     * @ORM\Column(name="arrivee", type="string", length=255)
     */
    private $arrivee;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="date_allee", type="datetime")
     */
    private $dateAllee;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="date_retour", type="datetime")
     */
    private $dateRetour;

    /**
     * @var string
     *
     * @ORM\Column(name="description", type="string", length=255)
     */
    private $description;

    /**
     * @var float
     *
     * @ORM\Column(name="latitude", type="float")
     */
    private $latitude1;

    /**
     * @var float
     *
     * @ORM\Column(name="longitude1", type="float")
     */
    private $longitude1;

    /**
     * @var float
     *
     * @ORM\Column(name="latitude2", type="float")
     */
    private $latitude2;

    /**
     * @var float
     *
     * @ORM\Column(name="longitude2", type="float")
     */
    private $longitude2;


    /**
     * Get id
     *
     * @return int
     */
    public function getId()
    {
        return $this->id;
    }

    /**
     * Set idEvent
     *
     * @param integer $idEvent
     *
     * @return Event
     */
    public function setIdEvent($idEvent)
    {
        $this->idEvent = $idEvent;

        return $this;
    }

    /**
     * Get idEvent
     *
     * @return int
     */
    public function getIdEvent()
    {
        return $this->idEvent;
    }

    /**
     * Set nom
     *
     * @param string $nom
     *
     * @return Event
     */
    public function setNom($nom)
    {
        $this->nom = $nom;

        return $this;
    }

    /**
     * Get nom
     *
     * @return string
     */
    public function getNom()
    {
        return $this->nom;
    }

    /**
     * Set nbrPlace
     *
     * @param integer $nbrPlace
     *
     * @return Event
     */
    public function setNbrPlace($nbrPlace)
    {
        $this->nbrPlace = $nbrPlace;

        return $this;
    }

    /**
     * Get nbrPlace
     *
     * @return int
     */
    public function getNbrPlace()
    {
        return $this->nbrPlace;
    }

    /**
     * Set depart
     *
     * @param string $depart
     *
     * @return Event
     */
    public function setDepart($depart)
    {
        $this->depart = $depart;

        return $this;
    }

    /**
     * Get depart
     *
     * @return string
     */
    public function getDepart()
    {
        return $this->depart;
    }

    /**
     * Set arrivee
     *
     * @param string $arrivee
     *
     * @return Event
     */
    public function setArrivee($arrivee)
    {
        $this->arrivee = $arrivee;

        return $this;
    }

    /**
     * Get arrivee
     *
     * @return string
     */
    public function getArrivee()
    {
        return $this->arrivee;
    }

    /**
     * Set dateAllee
     *
     * @param \DateTime $dateAllee
     *
     * @return Event
     */
    public function setDateAllee($dateAllee)
    {
        $this->dateAllee = $dateAllee;

        return $this;
    }

    /**
     * Get dateAllee
     *
     * @return \DateTime
     */
    public function getDateAllee()
    {
        return $this->dateAllee;
    }

    /**
     * Set dateRetour
     *
     * @param \DateTime $dateRetour
     *
     * @return Event
     */
    public function setDateRetour($dateRetour)
    {
        $this->dateRetour = $dateRetour;

        return $this;
    }

    /**
     * Get dateRetour
     *
     * @return \DateTime
     */
    public function getDateRetour()
    {
        return $this->dateRetour;
    }

    /**
     * Set description
     *
     * @param string $description
     *
     * @return Event
     */
    public function setDescription($description)
    {
        $this->description = $description;

        return $this;
    }

    /**
     * Get description
     *
     * @return string
     */
    public function getDescription()
    {
        return $this->description;
    }

    /**
     * Set latitude
     *
     * @param string $latitude
     *
     * @return Event
     */
    public function setLatitude($latitude)
    {
        $this->latitude = $latitude;

        return $this;
    }

    /**
     * Get latitude
     *
     * @return string
     */
    public function getLatitude()
    {
        return $this->latitude;
    }

    /**
     * Set longitude1
     *
     * @param string $longitude1
     *
     * @return Event
     */
    public function setLongitude1($longitude1)
    {
        $this->longitude1 = $longitude1;

        return $this;
    }

    /**
     * Get longitude1
     *
     * @return string
     */
    public function getLongitude1()
    {
        return $this->longitude1;
    }

    /**
     * Set latitude2
     *
     * @param string $latitude2
     *
     * @return Event
     */
    public function setLatitude2($latitude2)
    {
        $this->latitude2 = $latitude2;

        return $this;
    }

    /**
     * Get latitude2
     *
     * @return string
     */
    public function getLatitude2()
    {
        return $this->latitude2;
    }

    /**
     * Set longitude2
     *
     * @param string $longitude2
     *
     * @return Event
     */
    public function setLongitude2($longitude2)
    {
        $this->longitude2 = $longitude2;

        return $this;
    }

    /**
     * Get longitude2
     *
     * @return string
     */
    public function getLongitude2()
    {
        return $this->longitude2;
    }

    /**
     * Set latitude1
     *
     * @param string $latitude1
     *
     * @return Event
     */
    public function setLatitude1($latitude1)
    {
        $this->latitude1 = $latitude1;

        return $this;
    }

    /**
     * Get latitude1
     *
     * @return string
     */
    public function getLatitude1()
    {
        return $this->latitude1;
    }

    public function __toString()
    {
        return strval($this->idEvent);
    }


}
