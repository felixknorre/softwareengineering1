import java.util.ArrayList;
import java.util.List;

public class ParkhausCaller implements ParkhausCallerIF{
	
	List<CommandIF> commands;
	int pointer;
	
	public ParkhausCaller() {
		this.commands = new ArrayList<CommandIF>();
		this.pointer = -1;
	}
	
	@Override
	public void saveNewCommand(CommandIF c) {
		this.pointer++;
		this.commands.add(this.pointer, c);
	}

	@Override
	public void execute() {
		if((this.pointer < this.commands.size()) || !this.commands.isEmpty()) {
			this.commands.get(this.pointer).execute();
		}
		
	}

	@Override
	public void undo() {
		if(this.pointer  >= 0 ) {
			this.commands.get(this.pointer).undo();
			this.pointer--;
		}
		
	}

	@Override
	public void redo() {
		if((this.pointer + 1) < this.commands.size()) {
			this.pointer++;
			this.commands.get(this.pointer).execute();
		}
		
	}

}
