import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ManualDataComponent } from './manual-data.component';

describe('ManualDataComponent', () => {
  let component: ManualDataComponent;
  let fixture: ComponentFixture<ManualDataComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ManualDataComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ManualDataComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
