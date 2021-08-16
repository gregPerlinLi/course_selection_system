/**
 * <code>CourseSelectionService</code>的实现类
 *
 * @author gregPerlinLi
 * @see com.gregperlinli.service.CourseSelectionService
 * @since 2021-08-16
 */
public class CourseSelectionServiceImpl implements CourseSelectionService {
    private final CourseDao courseDao = new CourseDaoImpl();
    private final SelectedCourseDao selectedCourseDao = new SelectedCourseDaoImpl();
    private Connection conn = null;

    @Override
    public SelectedCourse selectCourse(int id) {
        return null;
    }

    @Override
    public boolean cancelSelection(int id) {
        return false;
    }

    @Override
    public List<Course> queryEnableCourse() {
    }
}
