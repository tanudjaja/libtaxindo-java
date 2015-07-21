
package id.web.tanudjaja.libtaxindo.constants;

public final class MaritalStatus
{
	// 4 msb indicates the marital status (0 for single, 1 for married)
	// 4 lsb indicates the number of children

	public final static byte UNMARRIED=0x00;
	public final static byte MARRIED_NO_CHILDREN=0x10;
	public final static byte MARRIED_ONE_CHILD=0x11;
	public final static byte MARRIED_TWO_CHILDREN=0x12;
	public final static byte MARRIED_THREE_OR_MORE_CHILDREN=0x13;
};

