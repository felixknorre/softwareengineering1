
public interface ParkhausCallerIF {
	public void saveNewCommand(CommandIF c);
	public void execute();
	public void undo();
	public void redo();

}
