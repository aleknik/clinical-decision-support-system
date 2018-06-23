import { Component, OnInit } from '@angular/core';
import { Medicine } from '../../shared/model/medicine.model';
import { MedicineService } from '../../core/http/medicine.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-medicine-list',
  templateUrl: './medicine-list.component.html',
  styleUrls: ['./medicine-list.component.css']
})
export class MedicineListComponent implements OnInit {

  medicines: Medicine[];

  constructor(private medicineService: MedicineService,
    private router: Router) { }

  ngOnInit() {
    this.findAllMedicines();
  }

  private findAllMedicines(): void {
    this.medicineService.findAll().subscribe(result => {
      this.medicines = result;
    });
  }

}
