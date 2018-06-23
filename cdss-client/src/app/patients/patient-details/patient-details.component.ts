import { Component, OnInit, OnDestroy } from '@angular/core';
import { Patient } from '../../shared/model/patient.model';
import { PatientService } from '../../core/http/patient.service';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Medicine } from '../../shared/model/medicine.model';
import { Ingredient } from '../../shared/model/ingredient.model';
import { MedicineService } from '../../core/http/medicine.service';
import { IngredientService } from '../../core/http/ingredient.service';
import { Subscription, Observable } from 'rxjs';
import { StompService } from '@stomp/ng2-stompjs';
import { Message } from '@stomp/stompjs';
import { Notification } from '../../shared/model/notification.model';

@Component({
  selector: 'app-patient-details',
  templateUrl: './patient-details.component.html',
  styleUrls: ['./patient-details.component.css']
})
export class PatientDetailsComponent implements OnInit, OnDestroy {

  // Stream of messages
  private subscription: Subscription;
  public messages: Observable<Message>;

  // Subscription status
  public subscribed: boolean;

  patientId: number;

  patient = new Patient();

  allMedicines = new Array<Medicine>();
  allIngredients = new Array<Ingredient>();

  selectedMedicineName: string;
  selectedIngredientName: string;

  selectedMedicines = Array<Medicine>();
  selectedIngredients = new Array<Ingredient>();

  constructor(private patientService: PatientService,
    private medicineService: MedicineService,
    private ingredientService: IngredientService,
    private route: ActivatedRoute,
    private router: Router,
    private toastr: ToastrService,
    private stompService: StompService) { }

  ngOnInit() {
    this.route.params.subscribe(params => {
      this.patientId = params['id'];
      this.getPatient();
      this.getAllMedicine();
      this.getAllIngredients();
      this.subscribed = false;
    });
  }

  ngOnDestroy() {
    this.unsubscribe();
  }

  public unsubscribe() {
    if (!this.subscribed) {
      return;
    }

    // This will internally unsubscribe from Stomp Broker
    // There are two subscriptions - one created explicitly, the other created in the template by use of 'async'
    this.subscription.unsubscribe();
    this.subscription = null;
    this.messages = null;

    this.subscribed = false;
  }

  public subscribe() {
    if (this.subscribed) {
      return;
    }

    // Stream of messages
    this.messages = this.stompService.subscribe(`/topic/${this.patientId}`);

    // Subscribe a function to be run on_next message
    this.subscription = this.messages.subscribe(this.on_next);

    this.subscribed = true;
  }

  public on_next = (message: Message) => {
    const notification = JSON.parse(message.body);
    this.toastr.warning(`Patient ${this.patient.firstName}
      ${this.patient.lastName}: ${notification.message},
        Time: ${new Date(notification.timeStamp).toLocaleString()}`);
  }

  allDiagnoses() {
    this.router.navigate(['patients', this.patientId, 'diagnoses']);
  }

  private getPatient(): void {
    this.patientService.findById(this.patientId).subscribe(result => {
      this.patient = result;
      this.selectedMedicines = this.patient.medicineAllergies;
      this.selectedIngredients = this.patient.ingredientAllergies;
    });
  }

  update(): void {
    this.patient.medicineAllergies = this.selectedMedicines;
    this.patient.ingredientAllergies = this.selectedIngredients;
    this.patientService.update(this.patientId, this.patient).subscribe(result => {
      this.toastr.success('Patient updated');
    });
  }

  private getAllMedicine(): void {
    this.medicineService.findAll().subscribe(result => {
      this.allMedicines = result;
    });
  }

  private getAllIngredients(): void {
    this.ingredientService.findAll().subscribe(result => {
      this.allIngredients = result;
    });
  }

  medicineSelected(event) {
    if (!this.selectedMedicines.some(x => x.id === event.itm.id)) {
      this.selectedMedicines.push(event.item);
    }
    this.selectedMedicineName = '';
  }

  ingredientSelected(event) {
    if (!this.selectedIngredients.some(x => x.id === event.itm.id)) {
      this.selectedIngredients.push(event.item);
    }
    this.selectedIngredientName = '';
  }

  removeMedicine(medicine: Medicine) {
    const index: number = this.selectedMedicines.indexOf(medicine);
    if (index !== -1) {
      this.selectedMedicines.splice(index, 1);
    }
  }

  removeIngredient(ingredient: Ingredient) {
    const index: number = this.selectedIngredients.indexOf(ingredient);
    if (index !== -1) {
      this.selectedIngredients.splice(index, 1);
    }
  }

  diagnosePatient() {
    this.router.navigate(['patients', this.patient.id, 'diagnoses', 'new']);
  }

}
