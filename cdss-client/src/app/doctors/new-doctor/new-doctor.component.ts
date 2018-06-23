import { Component, OnInit } from '@angular/core';
import { User } from '../../shared/model/user.model';
import { DoctorService } from '../../core/http/doctor.service';
import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';

@Component({
  selector: 'app-new-doctor',
  templateUrl: './new-doctor.component.html',
  styleUrls: ['./new-doctor.component.css']
})
export class NewDoctorComponent implements OnInit {

  user = new User();

  constructor(private doctorService: DoctorService,
    private toastr: ToastrService,
    private router: Router) { }

  ngOnInit() {
  }

  create() {
    this.doctorService.create(this.user).subscribe(result => {
      this.toastr.success('Doctor created');
      this.router.navigate(['doctors']);
    });
  }

}
