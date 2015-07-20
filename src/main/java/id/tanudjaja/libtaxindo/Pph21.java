
package id.tanudjaja.libtaxindo;

import id.tanudjaja.libtaxindo.constants.MaritalStatus;

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
	}

	public double getBrutto()
	{
		return mBrutto;
	}

	public double getNetto()
	{
		return mNetto;
	}

	private double calculateTaxableIncome()
	{
		double annualNetto=12.0*mNetto;

		return Math.floor(annualNetto-mNonTaxableIncome);
	}

	private double calculateTaxTariff()
	{
		double pkp=calculateTaxableIncome();

        	return new TaxRateCounter(new TaxRate2013Rules()).calculate(pkp);
	}

	public double calculate()
	{
		double annualTax=calculateTaxTariff() * calculateTaxableIncome();

		return annualTax / 12.0;
	}

};
