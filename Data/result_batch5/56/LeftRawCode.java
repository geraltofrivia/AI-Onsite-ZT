// https://github.com/microsoft/malmo/tree/b59258d812a2600c0c615358684fcf660d83455c/Minecraft/src/main/java/com/microsoft/Malmo/Client/MalmoModClient.java#L53-L69
public class TempClass {
        @Override
        public void mouseXYChange()
        {
            if (this.isOverriding)
            {
                this.deltaX = 0;
                this.deltaY = 0;
                if (Mouse.isGrabbed())
                    Mouse.setGrabbed(false);
            }
            else
            {
                super.mouseXYChange();
                if (this.observer != null)
                    this.observer.onXYZChange(this.deltaX, this.deltaY, Mouse.getDWheel());
            }
        }

}