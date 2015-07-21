
package id.web.tanudjaja.libtaxindo;

public class TaxRateCounter
{
	public final static String INVALID_TAXABLE_INCOME="Taxable Income parameter should be greater than or equals with zero";

	private TaxRateRules mRules;
    
	public TaxRateCounter(TaxRateRules aRules)
	{
		mRules=aRules;
	}
    
	public double calculate(double aTaxableIncome)
	{
		int i, j;

		// validate taxable income value
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

		// check whether the income is less than first marker, if it is return immediately.
		if(aTaxableIncome <= markers[0])
		{
			return rates[0] * aTaxableIncome;
		}
		else
		{
			tariff += rates[0] * markers[0];
		}

		// calculate the tariff of each income layer separated by markers
		// if one is found not exceeding certain layer's marker, return immediately
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

		// calculate the remaining taxable income
		tariff += rates[rates.length-1] * (aTaxableIncome-markers[markers.length-1]);

		return tariff;
	}

	
};
