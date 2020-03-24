<?php

namespace VeloBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Location
 *
 * @ORM\Table(name="Location")
 * @ORM\Entity(repositoryClass="VeloBundle\Repository\LocationRepository")
 */
class Location
{
    /**
     * @var int
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="AUTO")
     * @ORM\Column(name="id_Location", type="integer")
     */
    private $idLocation;

    /**
     * @var int
     * @ORM\ManyToOne(targetEntity=AppBundle\Entity\user::class)
     * @ORM\JoinColumn(name="id_client" , referencedColumnName="id_user")
     */
    private $idClient;

    /**
     * @var int
     * @ORM\ManyToOne(targetEntity=Velo::class,)
     * @ORM\JoinColumn(name="id_velo" , referencedColumnName="id",onDelete="SET NULL")
     */
    private $idVelo;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="date_d", type="datetime")
     */
    private $dateD;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="date_f", type="datetime")
     */
    private $dateF;

    /**
     * @var float
     *
     * @ORM\Column(name="prix", type="float")
     */
    private $prix;


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
     * Set idLocation
     *
     * @param integer $idLocation
     *
     * @return Location
     */
    public function setIdLocation($idLocation)
    {
        $this->idLocation = $idLocation;

        return $this;
    }

    /**
     * Get idLocation
     *
     * @return int
     */
    public function getIdLocation()
    {
        return $this->idLocation;
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
     * Set idVelo
     *
     * @param integer $idVelo
     *
     * @return Location
     */
    public function setIdVelo($idVelo)
    {
        $this->idVelo = $idVelo;

        return $this;
    }

    /**
     * Get idVelo
     *
     * @return int
     */
    public function getIdVelo()
    {
        return $this->idVelo;
    }

    /**
     * Set dateD
     *
     * @param \DateTime $dateD
     *
     * @return Location
     */
    public function setDateD($dateD)
    {
        $this->dateD = $dateD;

        return $this;
    }

    /**
     * Get dateD
     *
     * @return \DateTime
     */
    public function getDateD()
    {
        return $this->dateD;
    }

    /**
     * Set dateF
     *
     * @param \DateTime $dateF
     *
     * @return Location
     */
    public function setDateF($dateF)
    {
        $this->dateF = $dateF;

        return $this;
    }

    /**
     * Get dateF
     *
     * @return \DateTime
     */
    public function getDateF()
    {
        return $this->dateF;
    }

    /**
     * Set prix
     *
     * @param float $prix
     *
     * @return Location
     */
    public function setPrix($prix)
    {
        $this->prix = $prix;

        return $this;
    }

    /**
     * Get prix
     *
     * @return float
     */
    public function getPrix()
    {
        return $this->prix;
    }
}

