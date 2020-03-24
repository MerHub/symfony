<?php

namespace AppBundle\Entity;

use Doctrine\ORM\Mapping as ORM;
use Symfony\Bridge\Doctrine\Validator\Constraints\UniqueEntity;
use Symfony\Component\Validator\Constraints as Assert;

/**
 * chauffeur
 *
 * @ORM\Table(name="chauffeur")
 * @ORM\Entity(repositoryClass="AppBundle\Repository\chauffeurRepository")
 * @UniqueEntity(
 *     fields={"cin"},
 *     message="this cin already exist"
 * )
 * @UniqueEntity(
 *     fields={"permis"},
 *     message="this permis already exist"
 * )
 */
class chauffeur
{

    /**
     * @var integer
     * @ORM\Id
     * @ORM\Column(name="id" , type="integer")
     */
    private $id_User;

    /**
     * @var string
     *
     * @ORM\Column(name="adresse", type="string", length=255, nullable=true)
     */
    private $adresse;

    /**
     * @var int
     *
     * @ORM\Column(name="cin", type="integer", unique=true, nullable=true)
     */
    private $cin;

    /**
     * @var string
     *
     * @ORM\Column(name="permis", type="string", length=255, unique=true, nullable=true)
     */
    private $permis;

    /**
     * @var string
     *
     * @ORM\Column(name="nom", type="string", length=255, nullable=true)
     */
    private $nom;

    /**
     * @var string
     *
     * @ORM\Column(name="prenom", type="string", length=255, nullable=true)
     */
    private $prenom;

    /**
     * @var string
     *
     * @ORM\Column(name="photo", type="string", length=255, nullable=true)
     */
    private $photo;

    /**
     * @var int
     *
     * @ORM\Column(name="ocuupe", type="integer", nullable=true)
     */
    private $ocuupe;


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
     * Set idUser
     *
     * @param integer $id-User
     *
     * @return chauffeur
     */
    public function setIdUser($idUser)
    {
        $this->id_User = $idUser;

        return $this;
    }

    /**
     * Get idUser
     *
     * @return int
     */
    public function getIdUser()
    {
        return $this->id_User;
    }

    /**
     * Set adresse
     *
     * @param string $adresse
     *
     * @return chauffeur
     */
    public function setAdresse($adresse)
    {
        $this->adresse = $adresse;

        return $this;
    }

    /**
     * Get adresse
     *
     * @return string
     */
    public function getAdresse()
    {
        return $this->adresse;
    }

    /**
     * Set cin
     *
     * @param integer $cin
     *
     * @return chauffeur
     */
    public function setCin($cin)
    {
        $this->cin = $cin;

        return $this;
    }

    /**
     * Get cin
     *
     * @return int
     */
    public function getCin()
    {
        return $this->cin;
    }

    /**
     * Set permis
     *
     * @param string $permis
     *
     * @return chauffeur
     */
    public function setPermis($permis)
    {
        $this->permis = $permis;

        return $this;
    }

    /**
     * Get permis
     *
     * @return string
     */
    public function getPermis()
    {
        return $this->permis;
    }

    /**
     * Set nom
     *
     * @param string $nom
     *
     * @return chauffeur
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
     * Set prenom
     *
     * @param string $prenom
     *
     * @return chauffeur
     */
    public function setPrenom($prenom)
    {
        $this->prenom = $prenom;

        return $this;
    }

    /**
     * Get prenom
     *
     * @return string
     */
    public function getPrenom()
    {
        return $this->prenom;
    }

    /**
     * Set photo
     *
     * @param string $photo
     *
     * @return chauffeur
     */
    public function setPhoto($photo)
    {
        $this->photo = $photo;

        return $this;
    }

    /**
     * Get photo
     *
     * @return string
     */
    public function getPhoto()
    {
        return $this->photo;
    }

    /**
     * Set ocuupe
     *
     * @param integer $ocuupe
     *
     * @return chauffeur
     */
    public function setOcuupe($ocuupe)
    {
        $this->ocuupe = $ocuupe;

        return $this;
    }

    /**
     * Get ocuupe
     *
     * @return int
     */
    public function getOcuupe()
    {
        return $this->ocuupe;
    }

    public function __toString()
    {
        return $this->username;
    }
}

