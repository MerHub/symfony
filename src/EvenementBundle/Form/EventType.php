<?php

namespace EvenementBundle\Form;

use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;

class EventType extends AbstractType
{
    /**
     * {@inheritdoc}
     */
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder->add('nom')->add('nbrPlace')->add('depart',null,[
            'attr'=>["class"=>"dontWrite"]
        ])->add('arrivee',null,[
            'attr'=>["class"=>"dontWrites"]
        ])->add('dateAllee')->add('dateRetour')->add('description')->add('latitude1')->add('longitude1')->add('latitude2')->add('longitude2');
    }/**
     * {@inheritdoc}
     */
    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults(array(
            'data_class' => 'EvenementBundle\Entity\Event'
        ));
    }

    /**
     * {@inheritdoc}
     */
    public function getBlockPrefix()
    {
        return 'evenementbundle_event';
    }


}
