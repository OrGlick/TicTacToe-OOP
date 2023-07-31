public class RendererFactory {


    public Renderer buildRenderer(String rendererType){
        switch (rendererType){
            case "console":
                return new ConsoleRenderer();
            case "void":
                return new VoidRenderer();
            default:
                return null;
        }
    }
}
