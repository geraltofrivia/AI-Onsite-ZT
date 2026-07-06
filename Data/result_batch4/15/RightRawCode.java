// https://github.com/EssentialsX/Essentials/tree/ff7c95271544c646ae3191ab4ba72f74b7c8f49a/Essentials/src/main/java/com/earth2me/essentials/utils/VersionUtil.java#L285-L298
public class TempClass {
        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder(major + "." + minor);
            if (patch != 0) {
                sb.append(".").append(patch);
            }
            if (preRelease != -1) {
                sb.append("-pre").append(preRelease);
            }
            if (releaseCandidate != -1) {
                sb.append("-rc").append(releaseCandidate);
            }
            return sb.append("-R").append(revision).toString();
        }

}