package id.tanudjaja.libtaxindo;

import static org.junit.Assert.*;
import org.junit.*;
import org.junit.rules.ExpectedException;
import static org.hamcrest.CoreMatchers.*;

import static id.tanudjaja.libtaxindo.constants.MaritalStatus.*;

public class TestPph21
{
	@Rule
	public ExpectedException mExceptionRule=ExpectedException.none();

	@Test
	public void testGetBrutto()
	{
		Pph21 p=new Pph21(20000000, 500000, 10000000, UNMARRIED, 150000);
		assertEquals(31580000, p.getBrutto(), 0.0);
	}

	@Test
	public void testGetNetto()
	{
		Pph21 p=new Pph21(20000000, 500000, 10000000, UNMARRIED, 150000);
		assertEquals(30530000, p.getNetto(), 0.0);
	}

	@Test
	public void testGetTaxableIncome()
	{
		Pph21 p=new Pph21(20000000, 500000, 10000000, UNMARRIED, 150000);
		assertEquals(342060000, p.getTaxableIncome(), 0.0);
	}

	@Test
	public void testGetTax()
	{
		Pph21 p=new Pph21(20000000, 500000, 10000000, UNMARRIED, 150000);
		assertEquals(55515000, p.getAnnualTax(), 0.0);
		assertEquals(4626250, p.getMonthlyTax(), 0.0);
	}
};

