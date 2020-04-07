<?php

namespace ReservationBundle\Form;

use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\HiddenType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;

class ReservationType extends AbstractType
{
    /**
     * {@inheritdoc}
     */
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder->add('depart', HiddenType::class)->add('arrive', HiddenType::class)->add('prix', null , [
            'attr' => ['class' => 'hide'],
        ])->add('typeReservation', HiddenType::class)->add('latitude', null , [
            'attr' => ['class' => 'hide'],
        ])->add('longitude', null , [
            'attr' => ['class' => 'hide'],
        ])->add('latitude2', null , [
            'attr' => ['class' => 'hide'],
        ])->add('longitude2', null , [
            'attr' => ['class' => 'hide'],
        ])->add('idClient', null , [
            'attr' => ['class' => 'hide'],
        ])->add('idChauffeur', null , [
            'attr' => ['class' => 'hide'],
        ]);
    }/**
     * {@inheritdoc}
     */
    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults(array(
            'data_class' => 'ReservationBundle\Entity\Reservation'
        ));
    }

    /**
     * {@inheritdoc}
     */
    public function getBlockPrefix()
    {
        return 'reservationbundle_reservation';
    }


}
