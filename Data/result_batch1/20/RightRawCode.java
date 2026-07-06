// https://github.com/Luohuayu/CatServer/tree/1c92118fcca69ffac97a48c8e1f6e1bb861b41d1/src/main/java/net/minecraftforge/common/animation/TimeValues.java#L281-L294
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
            ParameterValue other = (ParameterValue) obj;
            resolve();
            other.resolve();
            return Objects.equal(parameter, other.parameter);
        }

}