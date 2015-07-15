
package id.tanudjaja.libtaxindo;

public class TaxRateCounter
{
    public final static String INVALID_TAXABLE_INCOME="Taxable Income parameter should be greater than or equals with zero";
    
    private TaxRateRules mRules;
    
    public TaxRateCounter(TaxRateRules aRules)
    {
        mRules=aRules;
    }
    
    public double countTariff(double aTaxableIncome)
    {
        if(aTaxableIncome<0)
        {
            throw new IllegalArgumentException(INVALID_TAXABLE_INCOME);
        }
        else if(aTaxableIncome==0)
        {
            return 0;
        }
        
        double [] markers=mRules.getIntervalMarkers();
        double [] rate=mRules.getTaxRates();
        
        double taxableIncome=aTaxableIncome;
        double tariff=0;
       
        for(int i=markers.length; i>=0; i--)
        {
            double incomeAtThisLayer=taxableIncome-markers[i];
            if(incomeAtThisLayer<=0)
            {
                continue;
            }
            tariff+=rate[i+1] * incomeAtThisLayer;
            for(int j=i; j>=0; j--)
            {
                tariff+=rate[j] * markers[j];
            }
        }
        if(tariff==0)
        {
            tariff=rate[0] * taxableIncome;
        }
        
        return tariff;
    }
};
