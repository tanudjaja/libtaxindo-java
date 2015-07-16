
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
		int i, j;

		if(aTaxableIncome<0)
		{
			throw new IllegalArgumentException(INVALID_TAXABLE_INCOME);
		}
		else if(aTaxableIncome==0)
		{
			return 0;
		}

		double [] markers=mRules.getIntervalMarkers();
		double [] rates=mRules.getTaxRates();

		double tariff=0;

		if(aTaxableIncome <= markers[0])
		{
			return rates[0] * aTaxableIncome;
		}
		else
		{
			tariff += rates[0] * markers[0];
		}

		for(i=1; i<markers.length; i++)
		{
			if(aTaxableIncome <= markers[i])
			{
				tariff += rates[i] * (aTaxableIncome-markers[i-1]);
				return tariff;
			}
			else
			{
				tariff += rates[i] * (markers[i]-markers[i-1]);
			}				
		}

		tariff += rates[rates.length-1] * (aTaxableIncome-markers[markers.length-1]);

		return tariff;
	}

	
};
