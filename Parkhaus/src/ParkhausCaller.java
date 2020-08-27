import java.util.ArrayList;
import java.util.List;

public class ParkhausCaller implements ParkhausCallerIF{
	
	List<CommandIF> commands;
	int pointer;
	
	public ParkhausCaller() {
		this.commands = new ArrayList<CommandIF>();
		this.pointer = 0;
	}
	
	@Override
	public void saveNewCommand(CommandIF c) {
		this.commands.add(this.pointer, c);
	}

	@Override
	public void execute() {
		if(this.pointer < this.commands.size()) {
			this.commands.get(this.pointer).execute();
			this.pointer++;
		}
		
	}

	@Override
	public void undo() {
		if(--this.pointer  >= 0 ) {
			this.commands.get(this.pointer).undo();
		}
		
	}

	@Override
	public void redo() {
		if(this.pointer < this.commands.size()) {
			this.commands.get(this.pointer++).execute();
		}
		
	}

}
