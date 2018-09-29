package amulp.com.afit.models

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import io.objectbox.relation.ToMany

@Entity
data class Day(
        @Id var id: Long = 0,
        var name: String = "",

        var dayNumber: Int = 0
) {
    var exercises: ToMany<Exercise>? = null
}