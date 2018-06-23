import { Component, OnInit } from '@angular/core';
import { DoctorService } from '../../core/http/doctor.service';
import { Router } from '@angular/router';
import { User } from '../../shared/model/user.model';

@Component({
  selector: 'app-doctor-list',
  templateUrl: './doctor-list.component.html',
  styleUrls: ['./doctor-list.component.css']
})
export class DoctorListComponent implements OnInit {

  doctors: User[];

  constructor(private doctorService: DoctorService,
    private router: Router) { }

  ngOnInit() {
    this.findAllDoctors();
  }

  private findAllDoctors(): void {
    this.doctorService.findAll().subscribe(result => {
      this.doctors = result;
    });
  }

}
