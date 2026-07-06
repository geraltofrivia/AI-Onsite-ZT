// https://github.com/coobird/thumbnailator/tree/951d04f352d90f34420a82909c15453fee1b75ff/src/main/java/net/coobird/thumbnailator/util/exif/IfdStructure.java#L148-L167
public class TempClass {
	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		IfdStructure other = (IfdStructure) obj;
		if (count != other.count)
			return false;
		if (offsetValue != other.offsetValue)
			return false;
		if (tag != other.tag)
			return false;
		if (type != other.type)
			return false;
		return true;
	}

}