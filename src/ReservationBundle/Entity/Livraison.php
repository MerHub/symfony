<?php

namespace ReservationBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Livraison
 *
 * @ORM\Table(name="livraison")
 * @ORM\Entity(repositoryClass="ReservationBundle\Repository\LivraisonRepository")
 */
class Livraison
{
    /**
     * @var int
     *
     * @ORM\Column(name="id", type="integer")
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="AUTO")
     */
    private $id;

    /**
     * @var int
     * @ORM\OneToOne(targetEntity=Reservation::class)
     * @ORM\JoinColumn(name="idReservation" , referencedColumnName="id_Reservation", nullable=false)
     */
    private $idReservation;

    /**
     * @var string
     *
     * @ORM\Column(name="codeLivraison", type="string", length=255)
     */
    private $codeLivraison;

    /**
     * @var int
     *
     * @ORM\Column(name="etat", type="integer", options={"default": 0})
     */
    private $etat;

    /**
     * @return int
     */
    public function getEtat()
    {
        return $this->etat;
    }

    /**
     * @param int $etat
     */
    public function setEtat($etat)
    {
        $this->etat = $etat;
    }



    /**
     * Get id.
     *
     * @return int
     */
    public function getId()
    {
        return $this->id;
    }

    /**
     * Set idReservation.
     *
     * @param int $idReservation
     *
     * @return Livraison
     */
    public function setIdReservation($idReservation)
    {
        $this->idReservation = $idReservation;

        return $this;
    }

    /**
     * Get idReservation.
     *
     * @return int
     */
    public function getIdReservation()
    {
        return $this->idReservation;
    }

    /**
     * Set codeLivraison.
     *
     * @param string $codeLivraison
     *
     * @return Livraison
     */
    public function setCodeLivraison($codeLivraison)
    {
        $this->codeLivraison = $codeLivraison;

        return $this;
    }

    /**
     * Get codeLivraison.
     *
     * @return string
     */
    public function getCodeLivraison()
    {
        return $this->codeLivraison;
    }

    public function __toString()
    {
        return $this->id;
    }


}
