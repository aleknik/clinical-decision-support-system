import { Component, OnInit } from '@angular/core';
import { Disease } from '../../shared/model/disease.model';
import { DiseaseService } from '../../core/http/disease.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-disease-list',
  templateUrl: './disease-list.component.html',
  styleUrls: ['./disease-list.component.css']
})
export class DiseaseListComponent implements OnInit {

  diseases: Disease[];

  constructor(private diseaseService: DiseaseService,
    private router: Router) { }

  ngOnInit() {
    this.findAllDiseases();
  }

  private findAllDiseases(): void {
    this.diseaseService.findAll().subscribe(result => {
      this.diseases = result;
    });
  }
}
