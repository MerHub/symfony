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
     * @ORM\JoinColumn(name="idSend" , referencedColumnName="id_user")
     */
    private $idSend;

    /**
     * @var int
     * @ORM\ManyToOne(targetEntity=AppBundle\Entity\user::class)
     * @ORM\JoinColumn(name="idReceive" , referencedColumnName="id_user", nullable=true)
     */
    private $idReceive;

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
     * @param int $idSend
     *
     * @return Notification
     */
    public function setIdSend($idSend)
    {
        $this->idSend = $idSend;

        return $this;
    }

    /**
     * Get idSend.
     *
     * @return int
     */
    public function getIdSend()
    {
        return $this->idSend;
    }

    /**
     * Set idReceive.
     *
     * @param int $idReceive
     *
     * @return Notification
     */
    public function setIdReceive($idReceive)
    {
        $this->idReceive = $idReceive;

        return $this;
    }

    /**
     * Get idChauffeur.
     *
     * @return int
     */
    public function getIdReceive()
    {
        return $this->idReceive;
    }

    public function jsonSerialize (){
        return get_object_vars($this);
    }

}
