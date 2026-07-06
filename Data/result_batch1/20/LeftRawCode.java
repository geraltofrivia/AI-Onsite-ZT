// https://github.com/jenkinsci/jenkins/tree/235d237f32835ccc1064c95c701f331d7dece7a3/core/src/main/java/hudson/model/ParameterValue.java#L256-L269
public class TempClass {
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ParameterValue other = (ParameterValue) obj;
        if (!Objects.equals(name, other.name)) {
            return false;
        }
        return true;
    }

}