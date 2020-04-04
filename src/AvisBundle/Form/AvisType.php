<?php

namespace AvisBundle\Form;

use blackknight467\StarRatingBundle\Form\RatingType;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\TextareaType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;

class AvisType extends AbstractType
{
    /**
     * {@inheritdoc}
     */
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder->add('msg',TextareaType::class,[
            "attr"=>["placeholder"=>"Your message","class"=>"form-control autogrow","style"=>"resize: none"]
        ])->add('note',RatingType::class,[
            'stars' => 5,
        ])->add('idChauffeur',null,[
            "attr"=>["style"=>"display:none"]
        ])->add('idCclient',null,[
            "attr"=>["style"=>"display:none"]
        ]);
    }/**
     * {@inheritdoc}
     */
    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults(array(
            'data_class' => 'AvisBundle\Entity\Avis'
        ));
    }

    /**
     * {@inheritdoc}
     */
    public function getBlockPrefix()
    {
        return 'avisbundle_avis';
    }


}
