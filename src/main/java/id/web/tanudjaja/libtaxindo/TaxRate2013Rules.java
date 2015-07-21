
package id.web.tanudjaja.libtaxindo;

public class TaxRate2013Rules extends TaxRateRules
{
    public TaxRate2013Rules()
    {
        super(
        new double []
        {
            50000000.0,
            250000000.0,
            500000000.0
        },
        new double []
        {
            0.05,
            0.15,
            0.25,
            0.30
        });
    }
};
