/**
 * @Package: Complete Admin Responsive Theme
 * @Since: Complete Admin 1.0
 * This file is part of Complete Admin Responsive Theme.
 */


jQuery(function($) {

    'use strict';

    var CMPLTADMIN_SETTINGS = window.CMPLTADMIN_SETTINGS || {};

    /*--------------------------------
        Knob Chart
     --------------------------------*/
    CMPLTADMIN_SETTINGS.chartKnob = function() {


        if ($.isFunction($.fn.knob)) {

            $(".knob").knob({
                change: function(value) {
                    //console.log("change : " + value);
                },
                release: function(value) {
                    //console.log(this.$.attr('value'));
                    console.log("release : " + value);
                },
                cancel: function() {
                    console.log("cancel : ", this);
                },
                /*format : function (value) {
                    return value + '%';
                },*/
                draw: function() {

                    // "tron" case
                    if (this.$.data('skin') == 'tron') {

                        this.cursorExt = 0.3;

                        var a = this.arc(this.cv) // Arc
                            ,
                            pa // Previous arc
                            , r = 1;

                        this.g.lineWidth = this.lineWidth;

                        if (this.o.displayPrevious) {
                            pa = this.arc(this.v);
                            this.g.beginPath();
                            this.g.strokeStyle = this.pColor;
                            this.g.arc(this.xy, this.xy, this.radius - this.lineWidth, pa.s, pa.e, pa.d);
                            this.g.stroke();
                        }

                        this.g.beginPath();
                        this.g.strokeStyle = r ? this.o.fgColor : this.fgColor;
                        this.g.arc(this.xy, this.xy, this.radius - this.lineWidth, a.s, a.e, a.d);
                        this.g.stroke();

                        this.g.lineWidth = 2;
                        this.g.beginPath();
                        this.g.strokeStyle = this.o.fgColor;
                        this.g.arc(this.xy, this.xy, this.radius - this.lineWidth + 1 + this.lineWidth * 2 / 3, 0, 2 * Math.PI, false);
                        this.g.stroke();

                        return false;
                    }
                }
            });

            // Example of infinite knob, iPod click wheel
            var v, up = 0,
                down = 0,
                i = 0,
                $idir = $("div.idir"),
                $ival = $("div.ival"),
                incr = function() {
                    i++;
                    $idir.show().html("+").fadeOut();
                    $ival.html(i);
                },
                decr = function() {
                    i--;
                    $idir.show().html("-").fadeOut();
                    $ival.html(i);
                };
            $("input.infinite").knob({
                min: 0,
                max: 20,
                stopper: false,
                change: function() {
                    if (v > this.cv) {
                        if (up) {
                            decr();
                            up = 0;
                        } else {
                            up = 1;
                            down = 0;
                        }
                    } else {
                        if (v < this.cv) {
                            if (down) {
                                incr();
                                down = 0;
                            } else {
                                down = 1;
                                up = 0;
                            }
                        }
                    }
                    v = this.cv;
                }
            });


        }


    };



    /******************************
     initialize respective scripts 
     *****************************/
    $(document).ready(function() {
        CMPLTADMIN_SETTINGS.chartKnob();
        knob_clock();
    });

    $(window).resize(function() {});

    $(window).on('load', function () {});

});



function knob_clock() {
    var $s = $(".second"),
        $m = $(".minute"),
        $h = $(".hour");
    var d = new Date(),
        s = d.getSeconds(),
        m = d.getMinutes(),
        h = d.getHours();
    $s.val(s).trigger("change");
    $m.val(m).trigger("change");
    $h.val(h).trigger("change");
    setTimeout("knob_clock()", 1000);
}