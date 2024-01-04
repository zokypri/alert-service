package se.implementer.alertservice.repository

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import se.implementer.alertservice.model.Alert

@Repository
interface AlertRepository : CrudRepository<Alert, Long> {
    // Add custom methods if needed
}