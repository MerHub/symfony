<?php

namespace AppBundle\Entity;

use Doctrine\ORM\Mapping as ORM;
use SBC\NotificationsBundle\Model\BaseNotification;

/**
 * Notification
 *
 * @ORM\Table(name="notification")
 * @ORM\Entity(repositoryClass="AppBundle\Repository\NotificationRepository")
 */
class Notification extends BaseNotification implements \JsonSerializable
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
     * @ORM\ManyToOne(targetEntity=AppBundle\Entity\user::class)
     * @ORM\JoinColumn(name="idClient" , referencedColumnName="id_user")
     */
    private $idClient;

    /**
     * @var int
     * @ORM\ManyToOne(targetEntity=AppBundle\Entity\user::class)
     * @ORM\JoinColumn(name="idChauffeur" , referencedColumnName="id_user", nullable=true)
     */
    private $idChauffeur;

    /**
     * @var int
     *
     * @ORM\Column(name="idAdmin", type="integer", nullable=true)
     */
    private $idAdmin;



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
     * @return int
     */
    public function getIdAdmin()
    {
        return $this->idAdmin;
    }

    /**
     * @param int $idAdmin
     */
    public function setIdAdmin($idAdmin)
    {
        $this->idAdmin = $idAdmin;
    }

    /**
     * Set idClient.
     *
     * @param int $idClient
     *
     * @return Notification
     */
    public function setIdClient($idClient)
    {
        $this->idClient = $idClient;

        return $this;
    }

    /**
     * Get idClient.
     *
     * @return int
     */
    public function getIdClient()
    {
        return $this->idClient;
    }

    /**
     * Set idChauffeur.
     *
     * @param int $idChauffeur
     *
     * @return Notification
     */
    public function setIdChauffeur($idChauffeur)
    {
        $this->idChauffeur = $idChauffeur;

        return $this;
    }

    /**
     * Get idChauffeur.
     *
     * @return int
     */
    public function getIdChauffeur()
    {
        return $this->idChauffeur;
    }

    public function jsonSerialize (){
        return get_object_vars($this);
    }

}
