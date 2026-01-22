public class DeadlineTask extends Task {
  private String deadline;

  public DeadlineTask(String taskName, String deadline) {
    super(taskName, "D");
    this.deadline = deadline;
  }

  public String toString() {
    return "[D]" + super.toString() + " (by: " + deadline + ")";
  }
}
