<?php

namespace ReservationBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Reservation
 *
 * @ORM\Table(name="Reservation")
 * @ORM\Entity(repositoryClass="ReservationBundle\Repository\ReservationRepository")
 */
class Reservation
{
    /**
     * @var int
     *
     * @ORM\Column(name="id_Reservation", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idReservation;

    /**
     * @var int
     * @ORM\ManyToOne(targetEntity=AppBundle\Entity\Client::class)
     * @ORM\JoinColumn(name="id_client" , referencedColumnName="id")
     */
    private $idClient;

    /**
     * @var int
     * @ORM\ManyToOne(targetEntity=AppBundle\Entity\chauffeur::class)
     * @ORM\JoinColumn(name="id_chauffeur" , referencedColumnName="id")
     */
    private $idChauffeur;

    /**
     * @var string
     *
     * @ORM\Column(name="depart", type="string", length=255, nullable=true)
     */
    private $depart;

    /**
     * @var string
     *
     * @ORM\Column(name="arrive", type="string", length=255, nullable=true)
     */
    private $arrive;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="heure", type="datetime", options={"default": "CURRENT_TIMESTAMP"})
     */
    private $heure;

    /**
     * @var float
     *
     * @ORM\Column(name="prix", type="float", precision=10, scale=0, nullable=false)
     */
    private $prix;

    /**
     * @var string
     *
     * @ORM\Column(name="type_Reservation", type="string", length=255, nullable=false)
     */
    private $typeReservation;

    /**
     * @var float
     *
     * @ORM\Column(name="latitude", type="float", precision=10, scale=0, nullable=false)
     */
    private $latitude;

    /**
     * @var float
     *
     * @ORM\Column(name="longitude", type="float", precision=10, scale=0, nullable=false)
     */
    private $longitude;

    /**
     * @var float
     *
     * @ORM\Column(name="latitude2", type="float", precision=10, scale=0, nullable=false)
     */
    private $latitude2;

    /**
     * @var float
     *
     * @ORM\Column(name="longitude2", type="float", precision=10, scale=0, nullable=false)
     */
    private $longitude2;

    /**
     * @return int
     */
    public function getIdReservation()
    {
        return $this->idReservation;
    }

    /**
     * @param int $idReservation
     */
    public function setIdReservation($idReservation)
    {
        $this->idReservation = $idReservation;
    }

    /**
     * @return int
     */
    public function getIdClient()
    {
        return $this->idClient;
    }

    /**
     * @param int $idClient
     */
    public function setIdClient($idClient)
    {
        $this->idClient = $idClient;
    }

    /**
     * @return int
     */
    public function getIdChauffeur()
    {
        return $this->idChauffeur;
    }

    /**
     * @param int $idChauffeur
     */
    public function setIdChauffeur($idChauffeur)
    {
        $this->idChauffeur = $idChauffeur;
    }

    /**
     * @return string
     */
    public function getDepart()
    {
        return $this->depart;
    }

    /**
     * @param string $depart
     */
    public function setDepart($depart)
    {
        $this->depart = $depart;
    }

    /**
     * @return string
     */
    public function getArrive()
    {
        return $this->arrive;
    }

    /**
     * @param string $arrive
     */
    public function setArrive($arrive)
    {
        $this->arrive = $arrive;
    }

    /**
     * @return \DateTime
     */
    public function getHeure()
    {
        return $this->heure;
    }

    /**
     * @param \DateTime $heure
     */
    public function setHeure($heure)
    {
        $this->heure = $heure;
    }

    /**
     * @return float
     */
    public function getPrix()
    {
        return $this->prix;
    }

    /**
     * @param float $prix
     */
    public function setPrix($prix)
    {
        $this->prix = $prix;
    }

    /**
     * @return string
     */
    public function getTypeReservation()
    {
        return $this->typeReservation;
    }

    /**
     * @param string $typeReservation
     */
    public function setTypeReservation($typeReservation)
    {
        $this->typeReservation = $typeReservation;
    }

    /**
     * @return float
     */
    public function getLatitude()
    {
        return $this->latitude;
    }

    /**
     * @param float $latitude
     */
    public function setLatitude($latitude)
    {
        $this->latitude = $latitude;
    }

    /**
     * @return float
     */
    public function getLongitude()
    {
        return $this->longitude;
    }

    /**
     * @param float $longitude
     */
    public function setLongitude($longitude)
    {
        $this->longitude = $longitude;
    }

    /**
     * @return float
     */
    public function getLatitude2()
    {
        return $this->latitude2;
    }

    /**
     * @param float $latitude2
     */
    public function setLatitude2($latitude2)
    {
        $this->latitude2 = $latitude2;
    }

    /**
     * @return float
     */
    public function getLongitude2()
    {
        return $this->longitude2;
    }

    /**
     * @param float $longitude2
     */
    public function setLongitude2($longitude2)
    {
        $this->longitude2 = $longitude2;
    }


}

