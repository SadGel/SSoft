import javafx.concurrent.Task;
import htmlflow.HtmlView;

/**
 * Created by User15 on 14.07.2017.
 */
public class SSoft_run {

    public static void main(String[] args) {

        HtmlView<Task> taskView = new HtmlView<>();
        taskView
                .head()
                .title("Task Details")
                .linkCss("https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css");
        taskView
                .body().classAttr("container")
                .heading(1, "Task Details")
                .hr()
                .div()
                .text("Title: ").text(Task::getTitle)
                .br()
                .text("Description: ").text(Task::getDescription)
                .br()
                .text("Priority: ").text(Task::getPriority);

    }

}
