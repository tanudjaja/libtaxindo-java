
package id.web.tanudjaja.libtaxindo;

import id.web.tanudjaja.libtaxindo.constants.MaritalStatus;

public class Pph21
{
	private final double mBasicSalary;
	private final double mTkp;
	private final double mBonusThr;
	private final byte  mMaritalStatus;
	private final double mPension;

	private final double mNonTaxableIncome;
	private final double mJkk;
	private final double mJkm;
	private final double mJht;
	private final double mBrutto;
	private final double mPositionAllowance;
	private final double mNetto;
	private final double mTaxableIncome;
	private final double mTaxTariff;

	public Pph21(double aBasicSalary, double aTkp, double aBonusThr, byte aMaritalStatus, double aPension)
	{
		mBasicSalary=aBasicSalary;
		mTkp=aTkp;
		mBonusThr=aBonusThr;
		mMaritalStatus=aMaritalStatus;
		mPension=aPension;

		mNonTaxableIncome=NonTaxableIncomeCounter.calculate(mMaritalStatus);
		mJkk=0.024 * mBasicSalary;
		mJkm=0.03 * mBasicSalary;
		mJht=0.02 * mBasicSalary;
		mBrutto=mBasicSalary+mTkp+mBonusThr+mJkk+mJkm;
		mPositionAllowance=Math.min(500000, 0.05*mBrutto);
		mNetto=mBrutto-(mPositionAllowance+mJht+mPension);

		double annualNetto=12.0 * mNetto;
		mTaxableIncome=Math.floor(annualNetto-mNonTaxableIncome);
		
		double annualTariff=new TaxRateCounter(new TaxRate2013Rules()).calculate(mTaxableIncome);
		mTaxTariff=annualTariff / 12.0;
	}

	public double getBrutto()
	{
		return mBrutto;
	}

	public double getNetto()
	{
		return mNetto;
	}

	public double getTaxableIncome()
	{
		return mTaxableIncome;
	}

	public double getTax()
	{
		return mTaxTariff;
	}
};

